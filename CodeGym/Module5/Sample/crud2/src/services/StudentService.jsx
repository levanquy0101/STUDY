import axios from 'axios'

export const getAll = async ()=>{
    try {
        const temp = await axios.get("http://localhost:8000/students")
        return temp.data;      
    } catch (error) {
        console.log("Không lấy được dữ liệu")
        throw new Error(error.message)
    }
}
export const getFiltered = async (gender, startDate, endDate) => {
    try {
        let url = "http://localhost:8000/students";
        if (gender || startDate || endDate) {
            url += "?_sort=age";
        }
        if (gender) {
            url += `&gender=${gender}`;
        }
        if (startDate) {
            url += `&dateNH_gte=${startDate}`;
        }
        if (endDate) {
            url += `&dateNH_lte=${endDate}`;
        }

        const response = await axios.get(url);
        return response.data;
    } catch (error) {
        console.log("Không lấy được dữ liệu");
        throw new Error(error.message);
    }
}

export const save = async (item)=>{
    try {
        const temp = await axios.post("http://localhost:8000/students", item)
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
export const update = async (id, item)=>{
    try {
        const temp = await axios.put(`http://localhost:8000/students/${id}`, item)
        return temp.data;
    }catch (e){
        console.log(e)
    }
}

export const findById = async (id) => {
    try {
        const temp = await axios.get(`http://localhost:8000/students/${id}`);
        return temp.data;
    }catch (err){
        console.log(err);
    }
}

