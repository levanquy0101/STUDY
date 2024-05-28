import axios from "axios";

export const getAll = async ()=>{
    try {
        const temp = await axios.get("http://localhost:8000/classes");
        return temp.data;
    }catch(error){
        console.log(error);
    }
}