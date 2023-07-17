import React, { createContext, useState } from "react";

export interface User {
  token: any;
  accessToken: string;
  password: string;
  userName: string;
}

interface UserContextValue {
  user: User | null;
  setUser: React.Dispatch<React.SetStateAction<User | null>>;
}

const initialUserContextValue: UserContextValue = {
  user: null,
  setUser: () => {},
};

export const UserContext = createContext<UserContextValue>(
  initialUserContextValue
);

export const UserProvider = ({ children }: any) => {
  const [user, setUser] = useState<User | null>(null);

  const userContextValue: UserContextValue = {
    user,
    setUser,
  };

  return (
    <UserContext.Provider value={userContextValue}>
      {children}
    </UserContext.Provider>
  );
};
