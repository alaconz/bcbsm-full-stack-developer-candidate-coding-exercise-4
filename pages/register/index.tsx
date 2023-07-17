import { useState } from "react";
import { useRouter } from "next/router";

const Register: React.FC = () => {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("user");

  const router = useRouter();
  const register = async () => {
    try {
      const options = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: username,
          password: password,
          role: role,
        }),
      };

      fetch("http://localhost:8080/api/auth/register", options)
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            let status = response.json;
            alert(status);
            throw new Error("Error: " + response.json());
          }
        })
        .then((response) => {
          alert(response.response);
          router.push("/login");
        })
        .catch((err) => console.error(err));
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    register();
  };

  return (
    <div>
      <h1>Register</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>username:</label>
          <input
            type="username"
            value={username}
            onChange={(e) => setUserName(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Role:</label>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="user">User</option>
            <option value="admin">Admin</option>
          </select>
        </div>
        <button type="submit">Register</button>
        <button onClick={() => router.push("/login")}>Login</button>
      </form>
    </div>
  );
};

export default Register;
