/**
 * Scroll-triggered reveal animations using Intersection Observer
 * Lightweight, no dependencies required
 */
(function() {
    'use strict';

    // Configuration
    const config = {
        threshold: 0.1,      // Trigger when 10% visible
        rootMargin: '0px 0px -50px 0px'  // Trigger slightly before entering viewport
    };

    // Initialize when DOM is ready
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init);
    } else {
        init();
    }

    function init() {
        // Set up scroll reveal animations
        setupScrollReveal();

        // Auto-add reveal classes to common elements
        autoRevealElements();
    }

    function setupScrollReveal() {
        // Check for Intersection Observer support
        if (!('IntersectionObserver' in window)) {
            // Fallback: show all elements immediately
            document.querySelectorAll('.reveal, .reveal-left, .reveal-right, .reveal-scale, .stagger-children')
                .forEach(el => el.classList.add('visible'));
            return;
        }

        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                    // Optionally unobserve after revealing (better performance)
                    observer.unobserve(entry.target);
                }
            });
        }, config);

        // Observe all reveal elements
        document.querySelectorAll('.reveal, .reveal-left, .reveal-right, .reveal-scale, .stagger-children')
            .forEach(el => observer.observe(el));
    }

    function autoRevealElements() {
        // Auto-add reveal to common content sections
        const selectors = [
            '.documentation',
            '.features',
            '.card-shadow',
            '.card-custom',
            '.api',
            '.community',
            '.our-document',
            'section > .container'
        ];

        selectors.forEach(selector => {
            document.querySelectorAll(selector).forEach((el, index) => {
                // Don't add if already has reveal class
                if (!el.classList.contains('reveal') &&
                    !el.classList.contains('reveal-left') &&
                    !el.classList.contains('reveal-right') &&
                    !el.classList.contains('reveal-scale')) {

                    // Add staggered delay based on position
                    el.classList.add('reveal');
                    el.style.transitionDelay = `${index * 100}ms`;
                }
            });
        });

        // Re-run observer for newly added elements
        setupScrollReveal();
    }

    // Expose for manual triggering if needed
    window.http4kAnimations = {
        refresh: function() {
            setupScrollReveal();
        },
        reveal: function(element) {
            element.classList.add('visible');
        }
    };
})();
