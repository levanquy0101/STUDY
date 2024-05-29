import { useForm } from 'react-hook-form';
import { toast } from "react-toastify";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import * as ClassesService from '../services/ClassesService';
import * as StudentService from '../services/StudentService';

function Create(props) {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm()
    const [classes, setClasses] = useState([]);


    useEffect(() => {
        getAll()
    }, []);
    const getAll =  async ()=>{
        const data = await ClassesService.getAll()
        setClasses(data);
    }

    const onSubmit = async (data) => {
        try {
            data.class = JSON.parse(data.class);
            await StudentService.save(data);
            console.log(data);
        } catch (e) {
            toast.error("Thêm mới thất bại")
            console.log(e)
            return;
        }
        toast.success("Thêm mới thành công")
        navigate("/")
    };

    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div className='add-item'>
                        <h2>Tạo mới</h2>
                        <form className='form' onSubmit={handleSubmit(onSubmit)}>
                            <input {...register('code',{required: 'Không được để trống!'})} type="text" placeholder="Nhập mã học viên"/>
                            {errors.code && <small>{errors.code.message}</small>}

                            <input {...register('name',{required: 'Không được để trống!'})} type="text" placeholder='Nhập tên học viên'/>
                            {errors.name && <small>{errors.name.message}</small>}

                            <input {...register('age',{required: 'Không được để trống!'})} type="text" placeholder='Nhập tuổi'/>
                            {errors.age && <small>{errors.age.message}</small>}

                            <label htmlFor="">Giới tính:</label>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Nam' type="radio" /><span>Nam</span>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Nữ' type="radio" /><span>Nữ</span>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Khác' type="radio" /><span>Khác</span>
                            {errors.gender && <small>{errors.gender.message}</small>}

                            <input {...register('dateNH',{required:'Không được để trống!'})} type="date" placeholder='Nhập ngày nhập học' />
                            {errors.dateNH && <small>{errors.dateNH.message}</small>}

                            <select {...register('class',{required: 'Không được để trống!'})} id="">
                                <option value="" >-- Lựa chọn lớp học --</option>
                                {
                                    classes.map((item) =>  (
                                        <option value={JSON.stringify(item)} key={item.id}>{item.code}</option>
                                    ))
                                }
                            </select>
                            {errors.class && <small>{errors.class.message}</small>}
                            <button type="submit">Gửi</button>
                        </form>
                    </div>
                    <button onClick={() => navigate("/")} className='btn back'>Quay lại</button>
                </div>
            </div>
        </div>
    );
}

export default Create;