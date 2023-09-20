// 获取一下登录信息
import { getInfo } from "@/api/login";
// 获取一下所有学生的id用来后续进行身份的判断
import { studentListAll } from "@/api/student_management/scoreSearch";

let newList;

// 定义一个函数   因为是两个异步处理，所有没办法直接获取到异步处理的结果，只能通过封装一个Promise对象来.then使用最终结果
export function getoneStudent() {
  return new Promise((resolve, reject) => {
    studentListAll().then(response => {
      getInfo().then(infoResponse => {
        const newUserId = infoResponse.user.userId;
        const list = response.list; 
        //
        const newList = list.filter(item => item.userId === newUserId);
        if (newList.length > 0) {
          resolve(newList[0].studentId);
        } else {
          // reject(new Error("No matching student found"));
          resolve(131080)
        }
        
        resolve(newList[0].studentId);
      }).catch(error => {
        reject(error);
      });
    }).catch(error => {
      reject(error);
    });
  });
}
