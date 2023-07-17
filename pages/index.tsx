import { useRouter } from "next/router";
import { RatingTable } from "../components/ratings";
import { User, UserContext } from "@/context/UserContext";
import { useContext, useState } from "react";
import UserOptions from "@/components/Options";

export default function Home() {
  const { user } = useContext(UserContext);
  const router = useRouter();

  if (user !== null) {
    router.push("/dashboard");
  }
  return (
    <div className="flex h-screen bg-green-300">
      <div className="flex-1 flex flex-col overflow-hidden">
        <UserOptions />
        <div className="flex">
          <main className="flex  w-full bg-blue overflow-x-auto overflow-y-auto mb-14 content-center"></main>
        </div>
      </div>
    </div>
  );
}
