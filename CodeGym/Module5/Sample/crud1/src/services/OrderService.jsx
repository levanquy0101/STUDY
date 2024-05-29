import axios from "axios";

export const save = async (item) => {
    try {
        const temp = await axios.post("http://localhost:8000/orders",item);
        return temp.data;
    } catch (e) {
        console.log(e)
        throw new Error("Không thể thêm mới!")
    }
}
export const getAll = async () => {
    try {
        const temp = await axios.get("http://localhost:8000/orders");
        return temp.data;
    } catch (e) {
        console.log(e)
        return [];
    }
}
export const remove = async (id) => {
    try {
        const temp = await axios.delete(`http://localhost:8000/orders/${id}`);
        return temp.data;
    } catch (e) {
        console.log(e)
        console.warn("Không thể xóa đối tượng!")
    }
}

export const update = async (id, item) => {
    try {
        const temp = await axios.put(`http://localhost:8000/orders/${id}`, item);
        return temp.data;
    } catch (e) {
        console.log(e)
        throw new Error("Không thể cập nhật!")
    }
}

export const search = async (s) => {
    try {
        const temp = await axios.get(`http://localhost:8000/orders?product.type=${s}`);
        return temp.data;
    } catch (e) {
        console.log(e);
        return [];
    }
}
