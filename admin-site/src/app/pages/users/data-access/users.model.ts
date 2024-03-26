export interface UsersList {
  content: User[];
  page: number;
  size: number;
}

export interface User {
  id: string;
  email: string;
  fullName: string;
  role: string;
  moduleAccess: string[];
}

export interface CreateUserRequest extends Omit<User, 'id'> {
  password: string;
}

export interface UpdateUserRequest extends Omit<User, 'id'> {}
