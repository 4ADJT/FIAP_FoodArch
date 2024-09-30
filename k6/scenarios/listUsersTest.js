import http from 'k6/http';
import { check } from 'k6';

export const options = {
    stages: [
        { duration: '5s', target: 10 },
    ],
};

const BASE_URL = 'http://localhost:8080';

const ENDPOINTS = {
    listUsers: '/users',
};

export default function () {
    const page = 0;
    const size = 10;
    const sort = 'name,asc';

    // Construir a URL com parâmetros de query
    const url = `${BASE_URL}${ENDPOINTS.listUsers}?page=${page}&size=${size}&sort=${sort}`;

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.get(url, params);

    check(res, {
        'Status code é 200': (r) => r.status === 200,
        'Resposta contém lista de usuários': (r) => {
            const body = JSON.parse(r.body);
            return Array.isArray(body.content);
        },
    });

    // Log para monitoramento
    if (res.status === 200) {
        console.log(`Lista de usuários recuperada com sucesso.`);
    } else {
        console.log(`Falha ao recuperar lista de usuários.`);
    }
}
