export function createCpf() {
    function randomDigits(n) {
        let digits = '';
        for (let i = 0; i < n; i++) {
            digits += Math.floor(Math.random() * 10);
        }
        return digits;
    }

    function calculateDigit(cpf, factor) {
        let total = 0;
        for (let i = 0; i < cpf.length; i++) {
            total += parseInt(cpf[i]) * factor--;
        }
        let remainder = total % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    let cpf = randomDigits(9);
    cpf += calculateDigit(cpf, 10);
    cpf += calculateDigit(cpf, 11);

    return cpf;
}
