<#macro layout title js="" headerJs="" sideNav="">
    <!doctype html>
    <html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Work+Sans:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
        <style type="text/tailwindcss">
            @import "tailwindcss";
            @theme {
                /* Colors */
                --color-brand-primary: var(--color-brand-600);
                --color-default-font: var(--color-neutral-900);
                --color-subtext-color: var(--color-neutral-500);
                --color-neutral-border: var(--color-neutral-200);
                --color-white: var(--color-neutral-0);
                --color-default-background: var(--color-neutral-0);
                --color-brand-50: rgb(240 253 244);
                --color-brand-100: rgb(220 252 231);
                --color-brand-200: rgb(187 247 208);
                --color-brand-300: rgb(134 239 172);
                --color-brand-400: rgb(74 222 128);
                --color-brand-500: rgb(34 197 94);
                --color-brand-600: rgb(22 163 74);
                --color-brand-700: rgb(21 128 61);
                --color-brand-800: rgb(22 101 52);
                --color-brand-900: rgb(20 83 45);
                --color-neutral-0: rgb(255 255 255);
                --color-neutral-50: rgb(248 250 252);
                --color-neutral-100: rgb(241 245 249);
                --color-neutral-200: rgb(226 232 240);
                --color-neutral-300: rgb(203 213 225);
                --color-neutral-400: rgb(148 163 184);
                --color-neutral-500: rgb(100 116 139);
                --color-neutral-600: rgb(71 85 105);
                --color-neutral-700: rgb(51 65 85);
                --color-neutral-800: rgb(30 41 59);
                --color-neutral-900: rgb(15 23 42);
                --color-neutral-950: rgb(2 6 23);
                --color-error-50: rgb(254 242 242);
                --color-error-100: rgb(254 226 226);
                --color-error-200: rgb(254 202 202);
                --color-error-300: rgb(252 165 165);
                --color-error-400: rgb(248 113 113);
                --color-error-500: rgb(239 68 68);
                --color-error-600: rgb(220 38 38);
                --color-error-700: rgb(185 28 28);
                --color-error-800: rgb(153 27 27);
                --color-error-900: rgb(127 29 29);
                --color-warning-50: rgb(245 243 255);
                --color-warning-100: rgb(237 233 254);
                --color-warning-200: rgb(221 214 254);
                --color-warning-300: rgb(196 181 253);
                --color-warning-400: rgb(167 139 250);
                --color-warning-500: rgb(139 92 246);
                --color-warning-600: rgb(124 58 237);
                --color-warning-700: rgb(109 40 217);
                --color-warning-800: rgb(91 33 182);
                --color-warning-900: rgb(76 29 149);
                --color-success-50: rgb(236 253 245);
                --color-success-100: rgb(209 250 229);
                --color-success-200: rgb(167 243 208);
                --color-success-300: rgb(110 231 183);
                --color-success-400: rgb(52 211 153);
                --color-success-500: rgb(16 185 129);
                --color-success-600: rgb(5 150 105);
                --color-success-700: rgb(4 120 87);
                --color-success-800: rgb(6 95 70);
                --color-success-900: rgb(6 78 59);

                /* Fonts */
                --text-caption: 12px;
                --text-caption--font-weight: 400;
                --text-caption--letter-spacing: 0em;
                --text-caption--line-height: 16px;
                --text-caption-bold: 12px;
                --text-caption-bold--font-weight: 500;
                --text-caption-bold--letter-spacing: 0em;
                --text-caption-bold--line-height: 16px;
                --text-body: 14px;
                --text-body--font-weight: 400;
                --text-body--letter-spacing: 0em;
                --text-body--line-height: 20px;
                --text-body-bold: 14px;
                --text-body-bold--font-weight: 500;
                --text-body-bold--letter-spacing: 0em;
                --text-body-bold--line-height: 20px;
                --text-heading-3: 16px;
                --text-heading-3--font-weight: 500;
                --text-heading-3--letter-spacing: 0em;
                --text-heading-3--line-height: 20px;
                --text-heading-2: 20px;
                --text-heading-2--font-weight: 500;
                --text-heading-2--letter-spacing: 0em;
                --text-heading-2--line-height: 24px;
                --text-heading-1: 30px;
                --text-heading-1--font-weight: 500;
                --text-heading-1--letter-spacing: 0em;
                --text-heading-1--line-height: 36px;
                --text-monospace-body: 14px;
                --text-monospace-body--font-weight: 400;
                --text-monospace-body--letter-spacing: 0em;
                --text-monospace-body--line-height: 20px;

                /* Font families */
                --font-caption: "Work Sans";
                --font-caption-bold: "Work Sans";
                --font-body: "Work Sans";
                --font-body-bold: "Work Sans";
                --font-heading-3: "Work Sans";
                --font-heading-2: "Work Sans";
                --font-heading-1: "Work Sans";
                --font-monospace-body: monospace;

                /* Box shadows */
                --shadow-sm: 0px 1px 2px 0px rgb(0 0 0 / 0.05);
                --shadow-default: 0px 1px 2px 0px rgb(0 0 0 / 0.05);
                --shadow-md: 0px 4px 16px -2px rgb(0 0 0 / 0.08),
                0px 2px 4px -1px rgb(0 0 0 / 0.08);
                --shadow-lg: 0px 12px 32px -4px rgb(0 0 0 / 0.08),
                0px 4px 8px -2px rgb(0 0 0 / 0.08);
                --shadow-overlay: 0px 12px 32px -4px rgb(0 0 0 / 0.08),
                0px 4px 8px -2px rgb(0 0 0 / 0.08);

                /* Border radiuses */
                --radius-sm: 8px;
                --radius-md: 16px;
                --radius-DEFAULT: 16px;
                --radius-lg: 24px;
                --radius-full: 9999px;

                /* Spacing */
                --spacing-112: 28rem;
                --spacing-144: 36rem;
                --spacing-192: 48rem;
                --spacing-256: 64rem;
                --spacing-320: 80rem;
            }

            /* Container */
            @utility container {
                padding-left: 16px;
                padding-right: 16px;

                @media (width >= 640px) {
                padding-left: calc((100vw + 16px - 640px) / 2);
                padding-right: calc((100vw + 16px - 640px) / 2);
            }

                @media (width >= 768px) {
                padding-left: calc((100vw + 16px - 768px) / 2);
                padding-right: calc((100vw + 16px - 768px) / 2);
            }

                @media (width >= 1024px) {
                padding-left: calc((100vw + 16px - 1024px) / 2);
                padding-right: calc((100vw + 16px - 1024px) / 2);
            }

                @media (width >= 1280px) {
                padding-left: calc((100vw + 16px - 1280px) / 2);
                padding-right: calc((100vw + 16px - 1280px) / 2);
            }

                @media (width >= 1536px) {
                padding-left: calc((100vw + 16px - 1536px) / 2);
                padding-right: calc((100vw + 16px - 1536px) / 2);
            }
            }

            @layer base {
                body {
                    font-family: var(--font-body);
                    color: var(--color-default-font);
                    background-color: var(--color-default-background);
                }
                h1 {
                    font-size: var(--text-heading-1);
                    line-height: var(--text-heading-1--line-height);
                    font-weight: var(--text-heading-1--font-weight);
                    font-family: var(--font-heading-1);
                }
                h2 {
                    font-size: var(--text-heading-2);
                    line-height: var(--text-heading-2--line-height);
                    font-weight: var(--text-heading-2--font-weight);
                    font-family: var(--font-heading-2);
                }
                h3 {
                    font-size: var(--text-heading-3);
                    line-height: var(--text-heading-3--line-height);
                    font-weight: var(--text-heading-3--font-weight);
                    font-family: var(--font-heading-3);
                }
            }

            @custom-variant mobile (@media (max-width: 767px));
        </style>

        ${headerJs}
    </head>
    <body class="bg-default-background text-default-font font-body">
    <div class="grid grid-cols-12 gap-2 mx-1">
        <#if sideNav?length gt 0>
            <aside class="col-span-3">
                ${sideNav}
            </aside>
            <section class="col-span-9">
                <#nested>
            </section>
        <#else>
            <section class="col-span-12">
                <#nested>
            </section>
        </#if>

        <@flashRemove />
    </div>
    </body>
    </html>
</#macro>

<#macro flashRemove time=5000>
    <script>
        setTimeout(function () {
            const flash = document.querySelectorAll(".flash");
            console.log(flash);
            flash.forEach(function (flash) {flash.remove();});
        }, ${time?c});
    </script>
</#macro>