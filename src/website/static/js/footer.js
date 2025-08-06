// Footer JavaScript functionality
$(document).ready(function () {
    $('.highlight').each(function () {
        const copyButton = $('<i class="fs-2 bi bi-clipboard copy-button"></i>');

        $(this).find("pre").prepend(copyButton);

        copyButton.on('click', function () {
            const $icon = $(this);
            const codeContent = $icon.parent().find('code').text();

            navigator.clipboard.writeText(codeContent)
                .then(() => {
                    $icon.removeClass('bi-clipboard').addClass('bi-clipboard-check-fill');

                    setTimeout(() => {
                        $icon.removeClass('bi-clipboard-check-fill').addClass('bi-clipboard');
                    }, 250);
                })
                .catch(err => {
                    console.error('Could not copy text: ', err);
                });
        });
    });
});

// HIDE THE PREAMBLE
document.querySelectorAll('span[style*="color:#66d9ef"]').forEach(span => {
        if (span.textContent === 'import' || span.textContent === 'package') {
            span.closest('span[style*="display:flex"]').style.display = 'none';
        }
    }
);