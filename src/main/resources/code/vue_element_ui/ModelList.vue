/*${cName}管理,作者:${auth},日期:${time}*/
<template>
  <div><h3>${cName}</h3>
    <hr/>
    <el-form :inline="true">
      <#list fList as fi>
      <el-form-item label="${fi.comment}">
        <el-input placeholder="请输入${fi.comment}" size="small" v-model="form.${fi.name}"></el-input>
      </el-form-item>
      </#list>
      <el-form-item>
        <el-button icon="search" @click="refresh(true)" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%" border >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form>
            <el-row :gutter="10">
              <#list fList as fi>
              <el-col :span="6">
                <el-form-item label="${fi.comment}">{{props.row.${fi.name}}}</el-form-item>
              </el-col>
              </#list>
			      </el-row>
          </el-form>
        </template>
      </el-table-column>
      <#list fList as fi>
      <el-table-column prop="${fi.name}" label="${fi.comment}"></el-table-column>
      </#list>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" @click="doDelete(props.row)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination layout="total, sizes, prev, pager, next, jumper" :current-page="page" :total="total" @current-change="(curr) => {this.page = curr ; this.refresh();}"
        small :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}" :page-size="size"></el-pagination>
      </div>
      <${upp}Dialog ref="dialog" :refresh="refresh"></${upp}Dialog>
    </div>
</template>
<script>
  import ${upp}Dialog from './${upp}Dialog.vue';

  export default {
    data: function () {
      return {
        total: 0,//记录个数
        page: 1,//当前页
        size: 20,//页大小
        dataList: [],//数据列表
        form: {
        <#list fList as fi>
		    ${fi.name} : null, // ${fi.comment}
        </#list>
        },
        loading: false,//是否加载列表
      }
    },
    computed: {},
    created: function () {
      this.refresh();
    },
    methods: {
      refresh(firstPage) {
        const that = this;
        that.page = (firstPage === true ? 1 : that.page);//点按钮的查询转到第一页
        that.loading = true;
        const requestData = {...that.form, page: that.page - 1, size: that.size};
        that.${dollar}http.post("/${lowUpp}/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.dataList;//数据列表
          that.total = res.data.rowCount;//记录个数
        }).catch(res => {
          that.${dollar}message.error("获取${cName}列表失败：" + res);
          that.loading = false;
        });
      },
      doAdd() {
        this.${dollar}refs["dialog"].addDialog();
      },
      doEdit(row) {
        this.${dollar}refs["dialog"].editDialog(row);
      },
      doDelete(row) {
        const that = this;
        this.${dollar}confirm('你确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          that.${dollar}http.delete("/${lowUpp}/delete?ids="+row.${idName}).then(res => {
			 that.${dollar}message.success("删除成功");
		     that.refresh();
          }).catch(res => {
             that.${dollar}message.error("删除失败：" + res);
          });
        }).catch(() => {

        });
      }
    },
    components: { ${upp}Dialog }
  }
</script>
<style></style>
