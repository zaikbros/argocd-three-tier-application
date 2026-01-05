import axios from 'axios';

const api = axios.create({
    baseURL: '/', // Relative path, handled by Ingress
});

export default api;
