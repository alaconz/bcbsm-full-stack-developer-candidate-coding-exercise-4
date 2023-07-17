import { RatingTable } from "@/components/ratings";
import { UserContext } from "@/context/UserContext";
import { useRouter } from "next/router";
import { useContext } from "react";

export default function Dashboard() {
  const { user, setUser } = useContext(UserContext);
  const router = useRouter();

  if (user == null) {
    router.push("/login");
  }
  return (
    <>
      <LogOut />
      <RatingTable />
    </>
  );
}
function LogOut() {
  const { user, setUser } = useContext(UserContext);
  return (
    <>
      <button
        onClick={() => {
          setUser(null);
        }}
      >
        log out
      </button>
    </>
  );
}
