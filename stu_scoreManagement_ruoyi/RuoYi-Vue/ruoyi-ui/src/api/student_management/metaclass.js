import request from '@/utils/request'

// 查询班级管理列表   完成
export function listMetaclass(query) {
  return request({
    url: '/student/metaclass/list',
    method: 'get',
    params: query
  })
}

//根据班级id获取到id的详细信息   完成
export function getMetaclass(meataclassId) {
  return request({
    url:`student/metaclass/oneMetaclass?meataclassId=${meataclassId}`,
    method: 'get'
  })
}

// 新增班级管理   完成
export function addMetaclass(query) {
  return request({
    url: '/student/metaclass/addMetaclass',
    method: 'post',
    params:query
  })
}

// 修改班级信息    完成
export function updateMetaclass(query) {
  return request({
    url: '/student/metaclass/modifyMetaclass',
    method: 'put',
    params:query
  })
}

// 删除班级根据 metaclassid  完成
export function delMetaclass(meataclassId) {
  return request({
    url:`student/metaclass/deleteMetaclass?meataclassId=${meataclassId}`,
    method: 'delete'
  })
}

// 根据学院id获取到学院下的所有班级   完成
export function collegeMetaclass(deptId) {
  return request({
    url:`student/metaclass/collegeMetaclass?deptId=${deptId}`,
    method: 'get'
  })
}

