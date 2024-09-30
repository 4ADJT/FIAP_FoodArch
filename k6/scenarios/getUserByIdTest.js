import http from 'k6/http';
import { check } from 'k6';
import { createFullNames } from './utils/createFullNames.js';
import { createEmail } from "./utils/createEmail.js";
import { createCpf } from './utils/createCpf.js';
import { createBDate } from './utils/createBDate.js';

export const options = {
    stages: [
        { duration: '10s', target: 10 },
    ],
};

const BASE_URL = 'http://localhost:8080';

export function setup() {
    // Cria um usuário
    const payload = JSON.stringify({
        name: createFullNames(),
        email: createEmail(),
        cpf: createCpf(),
        birthdate: createBDate(1980, 2000),
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(`${BASE_URL}/users`, payload, params);

    check(res, {
        'Criação de usuário: Status code é 201': (r) => r.status === 201,
    });

    if (res.status === 201) {
        const body = JSON.parse(res.body);
        return { userId: body.id };
    }

    return { userId: null };
}

export default function (data) {
    const userId = data.userId;

    if (!userId) {
        console.log('Falha ao criar usuário. Pulando teste.');
        return;
    }

    const res = http.get(`${BASE_URL}/users/${userId}`, {
        headers: {
            'Content-Type': 'application/json',
        },
    });

    check(res, {
        'Status code é 200': (r) => r.status === 200,
        'Resposta contém dados do usuário': (r) => {
            const body = JSON.parse(r.body);
            return body.id === userId;
        },
    });
}

export function teardown(data) {
    const userId = data.userId;

    if (userId) {
        http.del(`${BASE_URL}/users/${userId}`, null, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
    }
}
