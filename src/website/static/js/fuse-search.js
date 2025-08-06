// Fuse.js Search Implementation
(function() {
    'use strict';
    
    let fuseInstance;
    let searchData = [];

    // Initialize Fuse.js search
    async function initializeSearch() {
        try {
            const response = await fetch('/search-index.json');
            searchData = await response.json();
            
            const options = {
                keys: [
                    { name: 'title', weight: 0.4 },
                    { name: 'content', weight: 0.3 },
                    { name: 'section', weight: 0.2 },
                    { name: 'tags', weight: 0.1 }
                ],
                threshold: 0.4,
                includeScore: true,
                includeMatches: true,
                minMatchCharLength: 2,
                shouldSort: true,
                findAllMatches: true
            };
            
            fuseInstance = new Fuse(searchData, options);
            
            // Create search UI
            createSearchUI();
        } catch (error) {
            console.error('Failed to initialize search:', error);
        }
    }

    function createSearchUI() {
        const searchBox = document.getElementById('searchBox');
        if (!searchBox) return;

        // Create backdrop
        const backdrop = document.createElement('div');
        backdrop.className = 'search-backdrop';
        backdrop.id = 'search-backdrop';
        document.body.appendChild(backdrop);

        // Create unified search overlay with embedded results
        const searchOverlay = document.createElement('div');
        searchOverlay.className = 'search-overlay';
        searchOverlay.id = 'search-overlay';
        searchOverlay.innerHTML = `
            <div class="search-input-wrapper">
                <svg class="search-icon" width="20" height="20" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                </svg>
                <input type="text" id="search-overlay-input" placeholder="Search..." autocomplete="off">
            </div>
            <div class="search-overlay-hint">Press Escape to close • Start typing to search</div>
            <div class="search-results" id="search-results"></div>
        `;
        document.body.appendChild(searchOverlay);

        // Regular header search box
        searchBox.innerHTML = `
            <div class="search-container">
                <div class="search-input-wrapper">
                    <svg class="search-icon" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                    </svg>
                    <input type="text" id="search" class="form-control" placeholder="Search" autocomplete="off">
                    <div class="search-shortcut">
                        <span class="search-shortcut-key">⌘</span>
                        <span class="search-shortcut-key">K</span>
                    </div>
                </div>
            </div>
        `;

        const searchInput = document.getElementById('search');
        const overlaySearchInput = document.getElementById('search-overlay-input');
        const searchResults = document.getElementById('search-results');

        // Handle search only for overlay input
        function handleSearch(e) {
            const query = e.target.value.trim();
            
            if (query.length < 2) {
                searchResults.innerHTML = '';
                return;
            }

            const results = fuseInstance.search(query, { limit: 15 });
            
            // Sort results by date if available (newest first), then by search relevance
            const sortedResults = results.sort((a, b) => {
                const dateA = a.item.lastmod || a.item.date;
                const dateB = b.item.lastmod || b.item.date;
                
                if (dateA && dateB) {
                    const comparison = new Date(dateB) - new Date(dateA);
                    if (comparison !== 0) return comparison;
                }
                
                // If dates are equal or missing, sort by search score (lower is better in Fuse.js)
                return a.score - b.score;
            });
            
            displayResults(sortedResults, searchResults);
        }

        // Header search input should open overlay on click/focus
        searchInput.addEventListener('click', function(e) {
            e.preventDefault();
            showSearchOverlay();
        });
        
        searchInput.addEventListener('focus', function(e) {
            e.target.blur(); // Remove focus from header input
            showSearchOverlay();
        });

        // Make header input readonly to prevent typing
        searchInput.setAttribute('readonly', true);
        searchInput.style.cursor = 'pointer';

        // Only the overlay input handles actual searching
        overlaySearchInput.addEventListener('input', handleSearch);

        // Hide results when clicking backdrop
        backdrop.addEventListener('click', function(e) {
            if (e.target === backdrop) {
                hideSearchOverlay();
            }
        });

        // Handle keyboard shortcuts
        document.addEventListener('keydown', function(e) {
            if (e.key === 'Escape') {
                hideSearchOverlay();
            }
        });

        function showSearchOverlay() {
            searchOverlay.classList.add('show');
            backdrop.style.display = 'block';
            // Transfer any existing text from header search to overlay
            if (searchInput.value) {
                overlaySearchInput.value = searchInput.value;
                // Trigger search if there's already text
                const event = new Event('input');
                overlaySearchInput.dispatchEvent(event);
            }
            overlaySearchInput.focus();
        }

        function hideSearchOverlay() {
            searchOverlay.classList.remove('show');
            backdrop.style.display = 'none';
            overlaySearchInput.value = '';
            searchResults.innerHTML = '';
        }

        // Make functions globally available
        window.showSearchOverlay = showSearchOverlay;
        window.hideSearchOverlay = hideSearchOverlay;
    }

    function displayResults(results, container) {
        if (results.length === 0) {
            container.innerHTML = `
                <div class="search-results-header">
                    <h3 class="search-results-title">No results found</h3>
                    <div class="search-results-count">Try a different search term</div>
                </div>
            `;
            return;
        }

        const headerHtml = `
            <div class="search-results-header">
                <h3 class="search-results-title">Search Results</h3>
                <div class="search-results-count">${results.length} result${results.length === 1 ? '' : 's'} found</div>
            </div>
        `;

        const gridHtml = results.map(function(result) {
            const matchScore = Math.round((1 - result.score) * 100); // Convert Fuse.js score to percentage
            const section = result.item.section ? result.item.section.charAt(0).toUpperCase() + result.item.section.slice(1) : 'General';
            
            return `
                <div class="search-result-item">
                    <a href="${result.item.url}" class="search-result-link">
                        <div class="search-result-title">${result.item.title}</div>
                        <div class="search-result-content">${result.item.content}</div>
                        <div class="search-result-meta">
                            <span class="search-result-section">${section}</span>
                            <span class="search-match-score">${matchScore}% match</span>
                        </div>
                    </a>
                </div>
            `;
        }).join('');

        container.innerHTML = headerHtml + `<div class="search-results-grid">${gridHtml}</div>`;
    }

    // Global keyboard shortcut for search activation (Ctrl/Cmd + K)
    document.addEventListener('keydown', function(e) {
        if ((e.metaKey || e.ctrlKey) && e.key === 'k') {
            e.preventDefault();
            if (window.showSearchOverlay) {
                window.showSearchOverlay();
            }
        }
    });

    // Initialize search when DOM is loaded
    document.addEventListener('DOMContentLoaded', initializeSearch);
})();