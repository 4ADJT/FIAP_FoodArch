import http from 'k6/http';
import { check, sleep } from 'k6';
import { createFullNames } from './utils/createFullNames.js';
import { createBDate } from './utils/createBDate.js';

export const options = {
    stages: [
        { duration: '10s', target: 10 },
    ],
};

const BASE_URL = 'http://localhost:8080';

export default function () {
    // 1. Obter a lista de usuários
    const listRes = http.get(`${BASE_URL}/users`, {
        headers: { 'Content-Type': 'application/json' },
    });

    const listCheck = check(listRes, {
        'List Users - Status code é 200': (r) => r.status === 200,
        'List Users - Conteúdo é uma lista': (r) => {
            const body = JSON.parse(r.body);
            return Array.isArray(body.content);
        },
    });

    if (!listCheck) {
        console.log('Falha ao obter a lista de usuários.');
        sleep(1); // Pausa antes da próxima iteração
        return;
    }

    const users = JSON.parse(listRes.body).content;

    if (users.length === 0) {
        console.log('Nenhum usuário disponível para atualização.');
        sleep(1); // Pausa antes da próxima iteração
        return;
    }

    // 2. Selecionar o primeiro usuário da lista
    const user = users[0];
    const userId = user.id;
    const originalEmail = user.email;
    const originalCpf = user.cpf;

    // 3. Preparar os dados atualizados (mantendo email e cpf originais)
    const updatedName = createFullNames();
    const updatedBirthdate = createBDate(1980, 2000); // Ajuste conforme permitido pela API

    const updatePayload = JSON.stringify({
        name: updatedName,
        birthdate: updatedBirthdate,
        email: originalEmail, // Mantém o email original
        cpf: originalCpf,     // Mantém o CPF original
    });

    console.log(`Atualizando usuário ID: ${userId}`);
    console.log(`Payload de atualização: ${updatePayload}`);

    // 4. Realizar a atualização do usuário
    const updateRes = http.put(`${BASE_URL}/users/${userId}`, updatePayload, {
        headers: { 'Content-Type': 'application/json' },
    });

    const updateCheck = check(updateRes, {

        'Update User - Dados atualizados corretamente (se 200)': (r) => {
            if (r.status === 200) {
                const body = JSON.parse(r.body);
                const isNameUpdated = body.name === updatedName;
                const isBirthdateUpdated = body.birthdate === updatedBirthdate;
                return isNameUpdated && isBirthdateUpdated;
            }
            return true; // Se for 400, não verifica os dados
        },
        'Update User - Mensagem de erro está presente (se 400)': (r) => {
            if (r.status === 400) {
                const body = JSON.parse(r.body);
                console.log(`Erro na atualização: ${body.error}`);
                return body.error !== undefined;
            }
            return true; // Se for 200, não verifica a mensagem de erro
        },
    });

    if ([200, 400].includes(updateRes.status)) {
        console.log(`Requisição de atualização com status ${updateRes.status}`);
    } else {
        console.log(`Requisição de atualização falhou com status ${updateRes.status}`);
    }

    // 5. (Opcional) Pausar antes da próxima iteração
    sleep(1);
}
