Prism.languages.latex = {
    'comment': /%.*?(\r?\n|$)$/m,
    'string': /(\$)(\\?.)*?\1/,
    'punctuation': /[{}]/,
    'selector': /\\[a-z;,:\.]*/i
};