import { UserContext } from "@/context/UserContext";
import { useContext, useState } from "react";

interface FeedbackInterface {
  rerender: Function;
  render: boolean;
}
export default function FeedbackForm({ rerender, render }: FeedbackInterface) {
  const { user, setUser } = useContext(UserContext);

  interface FormData {
    rating: number;
    feedback: string;
  }

  const initialFormData: FormData = {
    rating: 0,
    feedback: "",
  };

  const [formData, setFormData] = useState<FormData>(initialFormData);

  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const rate = async (
    rating: number,
    feedback: string,
    token: string | undefined
  ) => {
    const data = new URLSearchParams();
    data.append("rating", rating.toString());
    data.append("feedback", feedback);
    const url = `http://localhost:8080/v1/user/rate?${data.toString()}`;

    const requestOptions: RequestInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({}),
    };

    try {
      const response = await fetch(url, requestOptions);
      if (response.ok) {
        const data = await response.text();
      } else {
        console.error("Request failed:", response.status, response.statusText);
      }
    } catch (error) {
      console.error("Request error:", error);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    rate(formData.rating, formData.feedback, user?.accessToken);
    rerender(!render);
    setFormData(initialFormData);
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col">
      <div className="flex mb-4">
        <label htmlFor="rating" className="mr-2">
          Rating:
        </label>
        <input
          type="number"
          id="rating"
          name="rating"
          value={formData.rating}
          max={5}
          min={0}
          onChange={handleInputChange}
          className="border border-gray-300 px-2 py-1"
        />
      </div>
      <div className="flex mb-4">
        <label htmlFor="feedback" className="mr-2">
          Feedback:
        </label>
        <textarea
          id="feedback"
          name="feedback"
          value={formData.feedback}
          onChange={handleInputChange}
          className="border border-gray-300 px-2 py-1"
        />
      </div>
      <button
        type="submit"
        className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        Submit
      </button>
    </form>
  );
}
