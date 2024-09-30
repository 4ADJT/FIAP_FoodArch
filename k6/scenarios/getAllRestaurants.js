import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 10 },
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'],
    },
};

const BASE_URL = 'http://localhost:8080';

export default function () {
    const page = 0;
    const size = 10;
    const sort = 'name,asc';
    const url = `${BASE_URL}/restaurants?page=${page}&size=${size}&sort=${sort}`;

    const res = http.get(url, {
        headers: { 'Content-Type': 'application/json' },
    });

    const checks = check(res, {
        'List Restaurants - Status code é 200': (r) => r.status === 200,
        'List Restaurants - Conteúdo é uma lista': (r) => {
            try {
                const body = JSON.parse(r.body);
                return Array.isArray(body.content);
            } catch (e) {
                return false;
            }
        },
        'List Restaurants - Verificar estrutura dos restaurantes': (r) => {
            try {
                const body = JSON.parse(r.body);
                if (!Array.isArray(body.content)) return false;
                if (body.content.length === 0) return true;
                const restaurant = body.content[0];
                return (
                    restaurant.id !== undefined &&
                    restaurant.name !== undefined &&
                    restaurant.ownerId !== undefined
                );
            } catch (e) {
                return false;
            }
        },
    });

    if (!checks) {
        console.log('Falha nas verificações do endpoint de listagem de restaurantes.');
    } else {
        console.log('Listagem de restaurantes verificada com sucesso.');
    }

    sleep(1);
}
