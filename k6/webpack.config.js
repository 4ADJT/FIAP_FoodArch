const path = require('path');
const glob = require('glob');

module.exports = {
    mode: 'production',
    entry: glob.sync('./scenarios/*.js').reduce((entries, file) => {
        const name = path.basename(file, '.js');
        entries[name] = path.resolve(__dirname, file);
        return entries;
    }, {}),
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].bundle.js',
        libraryTarget: 'commonjs',
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/, // Exclui apenas o node_modules
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            ['@babel/preset-env', { targets: 'defaults' }],
                        ],
                    },
                },
            },
        ],
    },
    resolve: {
        extensions: ['.js'],
    },
    externals: ({ context, request }, callback) => {
        if (/^k6(\/.*)?$/.test(request)) {
            // Exclui apenas os módulos internos do k6
            return callback(null, `commonjs ${request}`);
        }
        // Inclui todos os outros módulos no bundle
        callback();
    },
};
