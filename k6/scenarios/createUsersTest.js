import http from 'k6/http';
import { check } from 'k6';
import { createCpf } from './utils/createCpf.js';
import { createFullNames } from './utils/createFullNames.js';
import { createBDate } from './utils/createBDate.js';
import { createEmail } from "./utils/createEmail";

export const options = {
    stages: [
        { duration: '10s', target: 10 },
    ],
};

const BASE_URL = 'http://localhost:8080';

const ENDPOINTS = {
    createUser: '/users',
};

export default function () {
    const cpf = createCpf();
    const name = createFullNames();
    const email = createEmail();
    const birthdate = createBDate(1980, 2000);

    const url = `${BASE_URL}${ENDPOINTS.createUser}`;

    const payload = JSON.stringify({
        name: name,
        email: email,
        cpf: cpf,
        birthdate: birthdate,
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'Status code é 201 ou 400': (r) => [201, 400].includes(r.status),
    });
}
