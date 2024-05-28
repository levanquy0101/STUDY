import { useParams,useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import * as StudentService from '../services/StudentService';
import Moment from 'moment';

function Create(props) {
    const { id } = useParams();
    const [studentDetail, setStudentDetail] = useState({});
    useEffect(() => {
        findById(id)
        console.log(studentDetail)
    }, []);

    const findById = async (id) => {
        const data = await  StudentService.findById(id)
        setStudentDetail(data)
    }

    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div className='add-item'>
                        <h2>Chi tiết học viên</h2>
                        {studentDetail.id &&
                            <div style={{textAlign:"left",padding:14}}>
                                <p>Mã học viên: {studentDetail.code}</p>
                                <p>Tên học viên: {studentDetail.name}</p>
                                <p>Tuổi: {studentDetail.age}</p>
                                <p>Giới tính: {studentDetail.gender}</p>
                                <p>Ngày nhập học: {Moment(studentDetail.dateNH).format("DD/MM/YYYY")}</p>
                                <p>Mã lớp: {studentDetail.class.code}</p>
                            </div>
                        }
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Create;