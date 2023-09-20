//第一步先获取登录信息
import { getInfo } from "@/api/login";
//第二步数 获取到所有老师的信息
import { listTeacher } from "../api/teacher_management_small/teacher";

//定义一个函数
export function getoneTeacher() {
    return new Promise((resolve, reject) => {
      listTeacher().then(response => {
        getInfo().then(infoResponse => {
          const newUserId = infoResponse.user.userId;
          const list = response.list; 
          const newList = list.filter(item => item.userId === newUserId);
          if (newList.length > 0) {
            resolve(newList[0].teacherId);
          } else {
            resolve(3756)
          }       
        //   resolve(newList[0].studentId);
        }).catch(error => {
          reject(error);
        });
      }).catch(error => {
        reject(error);
      });
    });
  }