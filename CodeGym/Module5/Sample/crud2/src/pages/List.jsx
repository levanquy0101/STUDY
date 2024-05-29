import {Link, useNavigate} from 'react-router-dom';
import React, {useEffect, useState} from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import * as StudentService from '../services/StudentService';
import CustomTable from "../hooks/useTable";
import {toast} from "react-toastify";
import { FaRegEdit, FaRegEye  } from "react-icons/fa";
import { MdDelete } from "react-icons/md";
import Moment from "moment";



function List(props) {
    const navigate = useNavigate();
    const [students, setStudents] = useState([])
    const [deleteStudent, setDeleteStudent] = useState([])
    const [filterGender, setFilterGender] = useState("");
    const [filterStartDate, setFilterStartDate] = useState("");
    const [filterEndDate, setFilterEndDate] = useState("");

    useEffect(() => {
        getAll();
    }, [filterGender, filterStartDate, filterEndDate]);
    
    const getAll = async () => {
        const data = await StudentService.getFiltered(filterGender, filterStartDate, filterEndDate);
        console.log(data);
        setStudents(data);
    }
    const handleChangeDel = (item)=>{
        setDeleteStudent(item)
    }

    const removeItem = async ()=>{
        console.log(deleteStudent)
        await StudentService.remove(deleteStudent.id)
        setDeleteStudent([])
        toast.success("Đã xóa thành công!")
        getAll()
    }
    // useEffect(() => {
    //     getAll()
    // }, []);

    // const getAll = async () => {
    //     const data = await StudentService.getAll();
    //     setStudents(data)
    // }

    const handleUpdate = (id)=>{
        console.log(id)
        navigate('/update', { state: { idUpdate: id } });
    }
    const handleGenderChange = (e) => {
        setFilterGender(e.target.value);
    }

    const handleStartDateChange = (e) => {
        setFilterStartDate(e.target.value);
    }

    const handleEndDateChange = (e) => {
        setFilterEndDate(e.target.value);
    }
    
    const columns = [
    { header: 'Mã học viên', key: 'code', render: (row) => row.code },
    { header: 'Tên học viên', key: 'name', render: (row) => row.name },
    { header: 'Tuổi', key: 'age', render: (row) => row.age },
    { header: 'Giới tính', key: 'gender', render: (row) => row.gender },
    { header: 'Ngày nhập học', key: 'dateNH', render: (row) => Moment(row.dateNH).format('DD/MM/YYYY') },
    { header: 'Lớp học', key: 'class', render: (row) => row.class.code },
    { header: 'Giáo viên giảng dạy', key: 'teacher', render: (row) => row.class.teacher },
    { header: 'Hành động', key: 'action', render: (row) => (
            <div>
                <Link to={`/view/${row.id}`}><FaRegEye size={24} color="blue" /></Link>
                <i onClick={() => handleUpdate(row.id)} className='btn update'><FaRegEdit size={24} style={{color:"orange"}}/></i>
                <i onClick={() => handleChangeDel(row)}><MdDelete size={24} color="red" cursor="pointer" /></i>
            </div>
        )
    },
    ]

    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div>
                        <Link to="/create" className='btn add'>Thêm mới</Link>
                        <h2>Danh sách</h2>
                        <div className='fillter'>
                            <select name="gender" value={filterGender} onChange={handleGenderChange}>
                                <option value="">Tất cả giới tính</option>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                                <option value="Khác">Khác</option>
                            </select>
                            <label htmlFor="">
                                Từ: <input type="date" value={filterStartDate} onChange={handleStartDateChange} />
                                Đến: <input type="date" value={filterEndDate} onChange={handleEndDateChange} />
                            </label>
                        </div>
                         <CustomTable data={students} itemsPerPage={4} columns={columns} />
                        {deleteStudent.id &&
                            <div className='frame-modal'>
                                <div className="modal-content">
                                    <span className="close" onClick={() => setDeleteStudent({})}>&times;</span>
                                    <p>Are you sure you want to delete this item?</p>
                                    <div className="button-container">
                                        <button className="btn delete" onClick={removeItem}>Xóa</button>
                                        <button className="btn cancel" onClick={() => setDeleteStudent({})}>Trở về</button>
                                    </div>
                                </div>
                            </div>
                        }
                    </div>
                </div>
            </div>
        </div>
    );
}

export default List;