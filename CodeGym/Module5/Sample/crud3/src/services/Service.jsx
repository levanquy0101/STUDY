import axios from "axios";


export const getAll = async () => {
  try {
      const temp = await axios.get('http://localhost:8080/');
      return temp.data;
  }catch (err){
      console.log(err);
  }
}
export const update = async (id) => {
    try {
        const temp = await axios.put(`http://localhost:8080/orders/${id}`);
        return temp.data;
    }catch (err){
        console.log(err);
    }
}