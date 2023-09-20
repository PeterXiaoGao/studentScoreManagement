import request from '@/utils/request'

// 查询学生成绩
export function searchScore(studentId) {
  return request({
    url: `/student/stuInfo/score?studentId=${studentId}`,
    method: 'get',
  })
}


// 获取所有学生list
export function studentListAll() {
  return request({
    url: "student/stuInfo/list",
    method: 'get',
  })
}
