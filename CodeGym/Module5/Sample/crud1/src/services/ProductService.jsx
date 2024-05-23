import axios from "axios";

export const getAll = async () => {
    try {
        const temp = await axios.get("http://localhost:8080/products");
        return temp.data;
    } catch (e) {
        console.log(e)
        return [];
    }
}