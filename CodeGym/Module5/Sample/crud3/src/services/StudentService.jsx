import axios from "axios";

export const getAll = async ()=>{
    try {
        const temp = await axios.get("http://localhost:8000/students")
        console.log(temp.data)
        return temp.data;
    }catch(error){
        console.log(error);
    }
}
export const remove = async (id) => {
    try {
        const temp = await axios.delete(`http://localhost:8000/students/${id}`);
        return temp.data;
    } catch (e) {
        console.log(e)
        console.warn("Không thể xóa đối tượng!")
    }
}