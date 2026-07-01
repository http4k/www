// Footer JavaScript functionality

// Copy helper: navigator.clipboard only exists in secure contexts (https /
// localhost). Fall back to execCommand for plain-http origins (e.g. LAN dev).
function copyText(text) {
    if (navigator.clipboard && navigator.clipboard.writeText) {
        return navigator.clipboard.writeText(text);
    }
    return new Promise((resolve, reject) => {
        try {
            const ta = document.createElement('textarea');
            ta.value = text;
            ta.style.position = 'fixed';
            ta.style.opacity = '0';
            document.body.appendChild(ta);
            ta.select();
            document.execCommand('copy');
            document.body.removeChild(ta);
            resolve();
        } catch (e) {
            reject(e);
        }
    });
}

function initCopyButtons() {
    document.querySelectorAll('.highlight').forEach(function (highlight) {
        const pre = highlight.querySelector('pre');
        if (!pre) return;

        const copyButton = document.createElement('i');
        copyButton.className = 'fs-2 bi bi-clipboard copy-button';
        pre.prepend(copyButton);

        copyButton.addEventListener('click', function () {
            const code = copyButton.parentElement.querySelector('code');
            const codeContent = code ? code.textContent : '';

            copyText(codeContent)
                .then(() => {
                    // Report which code snippets get copied, by language and page.
                    if (typeof gtag === 'function') {
                        gtag('event', 'copy_code', {
                            code_language: (code && code.dataset.lang) || 'unknown',
                            page_path: window.location.pathname,
                        });
                    }

                    copyButton.classList.remove('bi-clipboard');
                    copyButton.classList.add('bi-clipboard-check-fill');

                    setTimeout(() => {
                        copyButton.classList.remove('bi-clipboard-check-fill');
                        copyButton.classList.add('bi-clipboard');
                    }, 250);
                })
                .catch(err => {
                    console.error('Could not copy text: ', err);
                });
        });
    });
}

// Run now if the DOM is already parsed (external script can execute after
// DOMContentLoaded has fired), else wait - mirrors jQuery's $(document).ready.
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initCopyButtons);
} else {
    initCopyButtons();
}

// HIDE THE PREAMBLE
document.querySelectorAll('span[style*="color:#66d9ef"]').forEach(span => {
        if (span.textContent === 'import' || span.textContent === 'package') {
            span.closest('span[style*="display:flex"]').style.display = 'none';
        }
    }
);
