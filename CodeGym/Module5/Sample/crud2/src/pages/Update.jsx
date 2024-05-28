import { useForm } from 'react-hook-form';
import { toast } from "react-toastify";
import {useLocation, useNavigate} from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import * as ClassesService from '../services/ClassesService';
import * as StudentService from '../services/StudentService';

function Update(props) {
    const location = useLocation();
    console.log(location.state)
    const [dataUpdate, setDataUpdate] = useState(location.state);
    const navigate = useNavigate();
    const {register, handleSubmit, setValue, errors} = useForm();
    const [classes, setClasses] = useState([]);

    useEffect(() => {
        if (location.state) {
            const { id,code, name, age, gender,dateNH, class: studentClass } = location.state;
            setValue('id',id)
            setValue('code', code);
            setValue('name', name);
            setValue('age', age);
            setValue('gender', gender);
            setValue('dateNH', dateNH);
            setValue('class', JSON.stringify(studentClass));
        }
        getAll()
    }, []);
    const getAll =  async ()=>{
        const data = await ClassesService.getAll()
        setClasses(data);
    }

    const onSubmit = async (data) => {
        try {
            data.class = JSON.parse(data.class);
            await StudentService.update(data.id,data);
            console.log(data);
        } catch (e) {
            toast.error("Sửa thất bại")
            console.log(e)
            return;
        }
        toast.success("Sửa thành công")
        navigate("/")
    };

    if(location.state == null) {
        return (
            <div>Trang lỗi</div>
        )
    }

    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div className='add-item'>
                        <h2>Sửa dữ liệu</h2>
                        <form className='form' onSubmit={handleSubmit(onSubmit)}>
                            <input type="hidden" name="id" defaultValue={dataUpdate.id}/>
                            <input {...register('code')} type="text" placeholder="Nhập mã học viên" />
                            <input {...register('name')} type="text" placeholder='Nhập tên học viên' />
                            <input {...register('age')} type="text" placeholder='Nhập tuổi' />
                            <label htmlFor="">Giới tính:</label>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Nam' type="radio" /><span>Nam</span>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Nữ' type="radio" /><span>Nữ</span>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Khác' type="radio" /><span>Khác</span>
                            <input {...register('dateNH')} type="date" placeholder='Nhập ngày nhập học' />
                            <select {...register('class')} id="" >
                                <option value="">-- Lựa chọn lớp học --</option>
                                {
                                    classes.map((item) =>  (
                                        <option value={JSON.stringify(item)} key={item.id} selected={item.id == dataUpdate.class.id}>{item.code}</option>
                                    ))
                                }
                            </select>
                            <button type="submit">Gửi</button>
                        </form>
                    </div>
                    <button onClick={() => navigate("/")} className='btn back'>Quay lại</button>
                </div>
            </div>
        </div>
    );
}

export default Update;