import { useRouter } from "next/router";

export default function UserOptions() {
  const router = useRouter();

  return (
    <>
      <button onClick={() => router.push("login")}>Login</button>
      <button onClick={() => router.push("register")}>Register</button>
    </>
  );
}
