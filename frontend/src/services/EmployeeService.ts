import api from './api';
import type { Employee } from '../types/Employee';

const API_URL = '/api/employees';

export const getEmployees = async () => {
    return await api.get<Employee[]>(API_URL);
};

export const getEmployeesPaged = async (page: number, size: number) => {
    return await api.get(`${API_URL}/page?page=${page}&size=${size}`);
};

export const createEmployee = async (employee: Employee) => {
    return await api.post(API_URL, employee);
};

export const deleteEmployee = async (id: number) => {
    return await api.delete(`${API_URL}/${id}`);
};

export const updateEmployee = async (id: number, employee: Employee) => {
    return await api.put(`${API_URL}/${id}`, employee);
};

export const getEmployeeById = async (id: number) => {
    return await api.get<Employee>(`${API_URL}/${id}`);
};
