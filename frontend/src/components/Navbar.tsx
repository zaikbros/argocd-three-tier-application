import { Link } from 'react-router-dom';
import { Users, UserPlus } from 'lucide-react';

export const Navbar: React.FC = () => {
    return (
        <nav className="bg-white shadow-md">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between h-16">
                    <div className="flex">
                        <Link to="/" className="flex-shrink-0 flex items-center">
                            <Users className="h-8 w-8 text-blue-600" />
                            <span className="ml-2 text-xl font-bold text-gray-800">HR System</span>
                        </Link>
                        <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
                            <Link to="/" className="border-transparent text-gray-500 hover:border-blue-500 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium">
                                Employees
                            </Link>
                            <Link to="/add" className="border-transparent text-gray-500 hover:border-blue-500 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium">
                                <UserPlus className="w-4 h-4 mr-1" />
                                Add Employee
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    );
};

