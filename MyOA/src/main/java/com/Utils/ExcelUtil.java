package com.Utils;
import com.Pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
public class ExcelUtil {

    /**
     * excel2007及以上版本
     *
     * @param file
     * @return
     * @throws IOException
     */
    static List<UserInfo> xlsxImportExcel(MultipartFile file) throws IOException {
        XSSFWorkbook xwb = new XSSFWorkbook(file.getInputStream()); //获取excel工作簿

        XSSFSheet xssfSheet = xwb.getSheetAt(0); //获取excel的sheet

        if (xssfSheet == null) {
            return null;
        }
        System.out.println("No. of rows : " + xssfSheet.getLastRowNum());
        List<UserInfo> list = new ArrayList<UserInfo>();

        //循环获取excel第二行
        for (int rowNum = 1; rowNum <=xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            //获取该行的所有列单元格数量
            Object objs[] = new Object[xssfRow.getLastCellNum()];
            //从第二列获取数据
            for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
                XSSFCell xssCell = xssfRow.getCell(cellNum);
                if (xssCell == null) {
                    continue;
                }
                objs[cellNum] = getXSSFValue(xssCell);

            }
            UserInfo userInfo=new UserInfo();
            userInfo.setUserid(Integer.parseInt(objs[0].toString()));
            userInfo.setUsername(objs[1].toString());
            userInfo.setUserpass(objs[2].toString());
            userInfo.setSex(objs[3].toString());
            userInfo.setBirthday(objs[4].toString());
            userInfo.setDepartmentid(Integer.parseInt(objs[5].toString()));
            userInfo.setTelephone(objs[6].toString());
            userInfo.setAddress(objs[7].toString());
            userInfo.setEmail(objs[8].toString());
            userInfo.setEntertime(objs[9].toString());
            userInfo.setSalary(Integer.parseInt(objs[10].toString()));
            userInfo.setRoleid(Integer.parseInt(objs[11].toString()));
            userInfo.setState(Integer.parseInt(objs[12].toString()));
            userInfo.setImageurl(objs[13].toString());
            list.add(userInfo);  //将excel每一行的数据封装到user对象,并将user对象添加到list
        }
        return list;
    }

    /**
     * @param file
     * @return excel2003版本
     * @throws IOException
     */
    static List<UserInfo> xlsImportExcel(MultipartFile file) throws IOException {
//        log.info("excel2003版本");
//
//        Workbook wb = new HSSFWorkbook(file.getInputStream()); //获取excel工作簿
//
//        Sheet sheet = wb.getSheetAt(0);  //获取excel的sheet
//
//        if (sheet == null) {
//            return null;
//        }
//
//        List<User> list = new ArrayList<User>();
//
//        //循环获取excel每一行
//        for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
//            Row row = sheet.getRow(rowNum);
//            if (row == null) {
//                continue;
//            }
//
//            User user = new User();
//
//            //循环获取excel每一行的每一列
//            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
//                Cell cell = row.getCell(cellNum);
//                if (cell == null) {
//                    continue;
//                }
//
//                switch (cellNum) {
//                    case 0:
//                        user.setName(String.valueOf(getValue(cell)));
//                        break;
//                    case 1:
//                        user.setPhone(String.valueOf(getValue(cell)));
//                        break;
//                    case 2:
//                        //Integer类型需要自行处理
//                        Double xssfValue = (Double) getValue(cell);
//                        user.setAge(xssfValue.intValue());
//                        break;
//                }
//                log.info("内容：" + getValue(cell));
//
//            }
//            list.add(user);    //将excel每一行的数据封装到user对象,并将user对象添加到list
//        }
//        return list;
        return null;
    }

    public static List<UserInfo> importExcel(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();  //获得上传的excel文件名
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);  //获取上传的excel文件名后缀

        List<UserInfo> users = null;
        if ("xlsx".equals(fileSuffix)) {
            users = xlsxImportExcel(file);
        } else if ("xls".equals(fileSuffix)) {
            users = xlsImportExcel(file);
        }
        return users;
    }


    /**
     * excel值处理
     *
     * @param hssfCell
     * @return
     */
    public static Object getXSSFValue(XSSFCell hssfCell) {
        String value = "";
        CellType cellType = hssfCell.getCellType();
        switch (hssfCell.getCellType()) {
            case NUMERIC: //数字
                if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                    // 如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date);

                } else {// 纯数字
                    BigDecimal big = new BigDecimal(hssfCell.getNumericCellValue());
                    value = big.toString();
                    // 解决1234.0 去掉后面的.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            // 布尔类型
            case BOOLEAN:
                value = " " + hssfCell.getBooleanCellValue();
                break;
            // 公式类型
            case FORMULA:
                // 读公式计算值
                value = String.valueOf(hssfCell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = hssfCell.getStringCellValue().toString();
                }
                break;
            // 字符串类型
            case STRING:
                value = hssfCell.getStringCellValue().toString();
                break;
            default:
                value = hssfCell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;
    }

    /**
     * excel值处理
     *
     * @param hssfCell
     * @return
     */
    public static Object getValue(Cell hssfCell) {
        Object result = null;
        switch (hssfCell.getCellType()) {
            case NUMERIC: //数字
                result = hssfCell.getNumericCellValue();
                break;
            case BOOLEAN: //Boolean
                result = hssfCell.getBooleanCellValue();
                break;
            case ERROR: //故障
                result = hssfCell.getErrorCellValue();
                break;
            case FORMULA: //公式
                result = hssfCell.getCellFormula();
                break;
            case BLANK: //空值
                result = "";
                break;
            default: //字符串
                result = hssfCell.getStringCellValue();
        }
        return result;
    }


}