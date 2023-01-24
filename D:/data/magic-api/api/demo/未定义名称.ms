{
  "properties" : { },
  "id" : "e6820d1123684a47a37dc572b01f65f5",
  "script" : null,
  "groupId" : "f716a55157aa4699bd8dc3f00dbcc097",
  "name" : "未定义名称",
  "createTime" : 1674582065934,
  "updateTime" : null,
  "lock" : null,
  "createBy" : null,
  "updateBy" : null,
  "path" : "/demo",
  "method" : "GET",
  "parameters" : [ ],
  "options" : [ ],
  "requestBody" : null,
  "headers" : [ ],
  "paths" : [ ],
  "responseBody" : null,
  "description" : null,
  "requestBodyDefinition" : null,
  "responseBodyDefinition" : null
}
================================
var sql = """
select * from test_data
"""

return db.select(sql)