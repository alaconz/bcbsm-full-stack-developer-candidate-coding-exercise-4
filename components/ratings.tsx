import MUIDataTable, {
  FilterType,
  MUIDataTableOptions,
  Responsive,
} from "mui-datatables";
import { useState, useEffect, useContext } from "react";
import { Ratings } from "../api/UserActions";
import { UserContext } from "@/context/UserContext";
import FeedbackForm from "./feedbackForm";

export function RatingTable() {
  const [data, setData] = useState<Ratings[]>();
  const columns = ["rating", "user", "comment", "date"];
  const [rerender, setRerender] = useState<boolean>(false);
  const { user, setUser } = useContext(UserContext);

  useEffect(() => {
    fetchUserData(user?.accessToken, user?.token.role).then((res) =>
      setData(res)
    );
  }, [rerender]);
  const handleRerender = (bool: boolean) => {
    setRerender(bool);
  };
  const options: MUIDataTableOptions = {
    filterType: "dropdown" as FilterType,
    responsive: "standard" as Responsive,
    tableBodyMaxHeight: "80vh",
    rowsPerPageOptions: [10, 25, 50, 100, 500],
    rowsPerPage: 100,
    fixedHeader: true,
    fixedSelectColumn: true,
  };

  return (
    <>
      <MUIDataTable
        title={`${user?.token.role} Table`}
        data={data!}
        columns={columns!}
        options={options}
      />
      {user?.token.role == "user" && (
        <FeedbackForm render={rerender} rerender={handleRerender} />
      )}
    </>
  );
}

function fetchUserData(
  token: string | undefined,
  role: string
): Promise<Ratings[]> {
  return new Promise<Ratings[]>((resolve, reject) => {
    try {
      const options = {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };
      fetch(`http://localhost:8080/v1/${role}/ratings`, options)
        .then((response) => response.json())
        .then((response) => {
          resolve(response);
        })
        .catch((err) => {
          console.error(err);
          reject(err);
        });
    } catch (error) {
      console.error(error);
      reject(error);
    }
  });
}
