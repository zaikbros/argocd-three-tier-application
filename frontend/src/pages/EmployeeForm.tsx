import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import type { Employee } from '../types/Employee';
import { createEmployee, getEmployeeById, updateEmployee } from '../services/EmployeeService';
import { Save, X } from 'lucide-react';

export const EmployeeForm: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const isEditMode = !!id;

    const [employee, setEmployee] = useState<Employee>({
        empname: '',
        job: '',
        sal: 0,
        deptno: 0,
        vflag: 'no'
    });

    useEffect(() => {
        if (isEditMode) {
            loadEmployee(parseInt(id));
        }
    }, [id]);

    const loadEmployee = async (empId: number) => {
        try {
            const response = await getEmployeeById(empId);
            setEmployee(response.data);
        } catch (error) {
            console.error("Error loading employee", error);
        }
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setEmployee(prev => ({
            ...prev,
            [name]: name === 'sal' || name === 'deptno' ? parseFloat(value) : value
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            if (isEditMode) {
                await updateEmployee(parseInt(id), employee);
            } else {
                await createEmployee(employee);
            }
            navigate('/');
        } catch (error) {
            console.error("Error saving employee", error);
            alert("Failed to save employee");
        }
    };

    return (
        <div className="max-w-2xl mx-auto px-4 py-8">
            <div className="bg-white shadow-md rounded-lg p-6">
                <div className="flex justify-between items-center mb-6">
                    <h2 className="text-2xl font-bold text-gray-900">
                        {isEditMode ? 'Edit Employee' : 'Add New Employee'}
                    </h2>
                    <button onClick={() => navigate('/')} className="text-gray-400 hover:text-gray-500">
                        <X className="w-6 h-6" />
                    </button>
                </div>

                <form onSubmit={handleSubmit} className="space-y-6">
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Name</label>
                        <input
                            type="text"
                            name="empname"
                            required
                            value={employee.empname}
                            onChange={handleChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm p-2 border"
                        />
                    </div>

                    <div>
                        <label className="block text-sm font-medium text-gray-700">Job</label>
                        <input
                            type="text"
                            name="job"
                            required
                            value={employee.job}
                            onChange={handleChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm p-2 border"
                        />
                    </div>

                    <div>
                        <label className="block text-sm font-medium text-gray-700">Salary</label>
                        <input
                            type="number"
                            name="sal"
                            required
                            value={employee.sal}
                            onChange={handleChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm p-2 border"
                        />
                    </div>

                    <div>
                        <label className="block text-sm font-medium text-gray-700">Department No</label>
                        <input
                            type="number"
                            name="deptno"
                            required
                            value={employee.deptno}
                            onChange={handleChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm p-2 border"
                        />
                    </div>

                    <div className="flex justify-end space-x-3">
                        <button
                            type="button"
                            onClick={() => navigate('/')}
                            className="px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50"
                        >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            className="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700"
                        >
                            <Save className="w-4 h-4 mr-2" />
                            Save Employee
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};
