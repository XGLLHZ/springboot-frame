package org.huangzi.main.web.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.DictionaryEntity;
import org.huangzi.main.web.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/2/3 下午3:04
 * @description: 数据字典前端控制器
 */
@RestController
@RequestMapping("/admin/dict")
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    @LogAnnotation("字典列表")
    @RequestMapping("/list")
    public APIResponse dictList(@RequestBody DictionaryEntity dictionaryEntity) {
        return dictionaryService.dictList(dictionaryEntity);
    }

    @LogAnnotation("字典详情")
    @RequestMapping("/get")
    public APIResponse getDict(@RequestBody DictionaryEntity dictionaryEntity) {
        return dictionaryService.getDict(dictionaryEntity);
    }

    @LogAnnotation("新增字典")
    @RequestMapping("/insert")
    public APIResponse insertDict(@RequestBody DictionaryEntity dictionaryEntity) {
        return dictionaryService.insertDict(dictionaryEntity);
    }

    @LogAnnotation("删除字典")
    @RequestMapping("/delete")
    public APIResponse deleteDict(@RequestBody DictionaryEntity dictionaryEntity) {
        return dictionaryService.deleteDict(dictionaryEntity);
    }

    @LogAnnotation("修改字典")
    @RequestMapping("/update")
    public APIResponse updateDict(@RequestBody DictionaryEntity dictionaryEntity) {
        return dictionaryService.updateDict(dictionaryEntity);
    }

}
