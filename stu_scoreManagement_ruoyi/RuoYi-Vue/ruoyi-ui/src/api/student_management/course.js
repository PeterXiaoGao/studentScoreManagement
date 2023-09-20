import request from '@/utils/request'

// 查询课程查询列表
export function listCourse(studentId) {
  return request({
    url: `student/stuInfo/allCourse?studentId=${studentId}`,
    method: 'get',
  })
}

