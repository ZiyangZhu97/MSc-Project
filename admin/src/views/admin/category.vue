<template>
  <div>
    <div class="row">
      <div class="col-md-6">
        <p>
          <button v-on:click="add()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit"></i>
            Add
          </button>
          &nbsp;
          <button v-on:click="all()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-refresh"></i>
            Refresh
          </button>
        </p>

        <table id="level1-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Order</th>
            <th>Operation</th>
          </tr>
          </thead>

          <tbody>
          <tr v-for="category in level1" v-on:click="onClickLevel1(category)" v-bind:class="{'active' : category.id === active.id}">
            <td>{{category.id}}</td>
            <td>{{category.name}}</td>
            <td>{{category.sort}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="col-md-6">
        <p>
          <button v-on:click="add()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit"></i>
            Add
          </button>
        </p>

        <table id="level2-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Order</th>
            <th>Operation</th>
          </tr>
          </thead>

          <tbody>
          <tr v-for="category in level2">
            <td>{{category.id}}</td>
            <td>{{category.name}}</td>
            <td>{{category.sort}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>


    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Form</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">父id</label>
                <div class="col-sm-10">
                  <input v-model="category.parent" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="category.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="category.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">Save</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  export default {
    name: "business-category",
    data: function() {
      return {
        category: {},
        categorys: [],
        level1: [],
        level2: [],
        active: {},
      }
    },
    mounted: function() {
      let _this = this;
      _this.all();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("business-category-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.category = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(category) {
        let _this = this;
        _this.category = $.extend({}, category);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      all() {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response)=>{
          let resp = response.data;
          _this.categorys = resp.content;

          // 将所有记录格式化成树形结构
          _this.level1 = [];
          for (let i = 0; i < _this.categorys.length; i++) {
            let c = _this.categorys[i];
            if (c.parent === '00000000') {
              _this.level1.push(c);
              for (let j = 0; j < _this.categorys.length; j++) {
                let child = _this.categorys[j];
                if (child.parent === c.id) {
                  if (Tool.isEmpty(c.children)) {
                    c.children = [];
                  }
                  c.children.push(child);
                }
              }
            }
          }
        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
          || !Validator.require(_this.category.parent, "Parent ID")
          || !Validator.require(_this.category.name, "Name")
          || !Validator.length(_this.category.name, "Name", 1, 50)
        ) {
          return;
        }

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save', _this.category).then((response)=>{
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.all();
            Toast.success("Your file has been saved!");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("You won't be able to revert this!", function () {
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id).then((response)=>{
            let resp = response.data;
            if (resp.success) {
              _this.all();
              Toast.success("Your file has been deleted!");
            }
          })
        });
      },

      onClickLevel1(category) {
        let _this = this;
        _this.active = category;
        _this.level2 = category.children;
      }
    }
  }
</script>

<style scoped>
  .active td {
    background-color: #d6e9c6 !important;
  }
</style>