const { execSync } = require('child_process');
const glob = require('glob');
const path = require('path');

// Busca todos os arquivos .bundle.js na pasta dist
const bundleFiles = glob.sync('./dist/*.bundle.js');

bundleFiles.forEach((file) => {
    console.log(`Executando teste: ${file}`);
    try {
        execSync(`k6 run ${file}`, { stdio: 'inherit' });
    } catch (error) {
        console.error(`Erro ao executar o teste ${file}:`, error.message);
    }
});
