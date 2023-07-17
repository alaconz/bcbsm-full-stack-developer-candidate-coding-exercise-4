import { useContext, useState } from "react";
import { useRouter } from "next/router";
import { UserContext } from "@/context/UserContext";
import jwtDecode from "jwt-decode";

const Login = () => {
  const { user, setUser } = useContext(UserContext);
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  let responseOK = true;
  const router = useRouter();

  if (user) {
    router.push("/dashboard");
  }
  const login = async () => {
    try {
      const options = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      };

      fetch("http://localhost:8080/api/auth/login", options)
        .then((response) => {
          responseOK = response.ok;
          return response.json();
        })
        .then((response) => {
          if (responseOK) {
            const token = jwtDecode(response.accessToken);
            setUser({
              password: password,
              userName: username,
              token: token,
              accessToken: response.accessToken,
            });
            router.push("/dashboard");
          } else {
            alert(response.response);
          }
        })
        .catch((err) => console.error(err));
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    login();
  };

  return (
    <div>
      <h1>Login</h1>
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
          <button type="submit">Login</button>
          <button onClick={() => router.push("/register")}>Register</button>
        </div>
      </form>
    </div>
  );
};

export default Login;
