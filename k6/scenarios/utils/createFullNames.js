export function createFullNames() {
    const names = ['Ana', 'Bruno', 'Carlos', 'Diana', 'Eduardo', 'Fernanda', 'Gabriel', 'Helena', 'Isabela', 'João'];
    const lastNames = ['Silva', 'Souza', 'Costa', 'Santos', 'Oliveira', 'Pereira', 'Rodrigues', 'Almeida', 'Nascimento', 'Lima'];

    const name = names[Math.floor(Math.random() * names.length)];
    const lastName = lastNames[Math.floor(Math.random() * lastNames.length)];

    return `${name} ${lastName}`;
}
