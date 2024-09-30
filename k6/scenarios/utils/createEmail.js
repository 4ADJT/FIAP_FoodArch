export function createEmail() {
    const domains = ['exemplo.com', 'teste.com', 'email.com', 'domain.com'];
    const user = `user${Math.floor(Math.random() * 10000)}`;
    const domain = domains[Math.floor(Math.random() * domains.length)];

    return `${user}@${domain}`;
}
