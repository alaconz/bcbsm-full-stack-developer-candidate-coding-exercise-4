export interface Ratings{
  id:number,
  username:string,
  rating:number,
  date:Date,
  feedback:string,
  }



export const submitFeedback = async (rating: number, feedback: string,token:string) => {
    try {
      const params = new URLSearchParams();
      params.append('rating', rating.toString());
      params.append('feedback', token);
  
      const response = await fetch(`http://localhost:8080/v1/user/rate?${params.toString()}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,

        },
        body: JSON.stringify({}),
      });
  
      if (response.ok) {
        const data = await response.json();
        return data;
      } else {
        throw new Error('Request failed');
      }
    } catch (error) {
      console.error(error);
    }
  };

