Prism.languages.python = {
    'comment': {
        pattern: /(^|[^\\])#.*?(\r?\n|$)/,
        lookbehind: true
    },
    'string': /"""[\s\S]+?"""|'''[\s\S]+?'''|("|')(\\?.)*?\1/,
    'keyword': /\b(as|assert|break|class|continue|def|del|elif|else|except|exec|finally|for|from|global|if|import|in|is|lambda|pass|print|raise|return|try|while|with|yield)\b/,
    'boolean': /\b(True|False)\b/,
    'number': /\b-?(0[box])?(?:[\da-f]+\.?\d*|\.\d+)(?:e[+-]?\d+)?j?\b/i,
    'operator': /[-+]|<=?|>=?|!|={1,2}|&{1,2}|\|?\||\?|\*|\/|~|\^|%|\b(or|and|not)\b/,
    'punctuation': /[{}[\];(),.:]/
};

