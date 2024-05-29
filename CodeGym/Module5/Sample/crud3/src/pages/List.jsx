import { toast } from "react-toastify";
import { useNavigate, Link } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import * as StudentServices from '../services/StudentService';
import CustomTable from "../hooks/useTable";
import Moment from "moment";

function List(props) {
    const navigate = useNavigate();
    const [students, setStudents] = useState([]);
    const [selectedRows, setSelectedRows] = useState([]);
    const[showModal, setShowModal] = useState(false);

    useEffect(() => {
        getAll()
    }, []);

    const getAll = async () => {
        const studentList = await StudentServices.getAll();
        setStudents(studentList);
    }

    const handleChangeDel = (e) => {
        const value = e.target.value;
        setSelectedRows((prevSelectedRows) => {
            if (prevSelectedRows.includes(value)) {
                return prevSelectedRows.filter((id) => id !== value);
            } else {
                return [...prevSelectedRows, value];
            }
        });
    };

    const removeItem = async() =>{
        console.log(selectedRows)
        for (let i = 0; i < selectedRows.length; i++) {
            await StudentServices.remove(selectedRows[i])
        }
        setShowModal(false);
        toast.success("Đã xóa thành công!")
        getAll()
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
                    <input type={"checkbox"} onChange={(e) => handleChangeDel(e)} value={row.id} checked={selectedRows.includes(row.id)}  />
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
                        <CustomTable columns={columns} data={students} itemsPerPage={5} />
                        <button className='btn delete' onClick={() => selectedRows.length > 0 && setShowModal(true)}>Xóa</button>
                        {showModal && selectedRows.length > 0 &&
                        <div className="frame-modal">
                            <div className="modal-content">
                                <span className="close" onClick={()=>setShowModal(false)}>&times;</span>
                                <p>Are you sure you want to delete: {selectedRows.join(" and ")} ?</p>
                                <div className="button-container">
                                    <button className="btn delete" onClick={removeItem}>Delete</button>
                                    <button className="btn cancel" onClick={()=>setShowModal(false)}>Cancel</button>
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