package rikkesoft.trongvu.com.sqlite_832019;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rikkesoft.trongvu.com.sqlite_832019.DAO.NhanVienDAO;
import rikkesoft.trongvu.com.sqlite_832019.DTO.NhanVienDTO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NhanVienAdapter.DuLieu{

    private  Button btnThem;
    private  Button btnXoa;
    private  Button btnSua;
    private EditText etTenNV;

    private NhanVienDAO nhanVienDAO;

    private NhanVienAdapter nhanVienAdapter;
    private RecyclerView recyclerView;
    private List<NhanVienDTO> nhanVienDTOList;
    private NhanVienDTO nhanVienDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /*
    ánh xạ
     */
    private void init(){
        btnThem = findViewById(R.id.btn_Them);
        btnXoa = findViewById(R.id.btn_Xoa);
        btnSua = findViewById(R.id.btn_Sua);
        etTenNV = findViewById(R.id.et_NhapTenNhanVien);

        nhanVienDAO = new NhanVienDAO(this);
        nhanVienDAO.open();
        nhanVienAdapter = new NhanVienAdapter(this, this);
        recyclerView = findViewById(R.id.rc_dsnhanvien);
        nhanVienDTOList = new ArrayList<>();
        nhanVienDTOList = nhanVienDAO.layDanhSachNhanVien();
        recyclerView.setAdapter(nhanVienAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnThem.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnSua.setOnClickListener(this);
    }

    /*
            override onCick
         */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Them:
                nhanVienDTO = new NhanVienDTO();
                String tenNV = etTenNV.getText().toString();
                nhanVienDTO.setTenNhanVien(tenNV);
                boolean kiemtra = nhanVienDAO.themNhanVien(nhanVienDTO);
                if(kiemtra){
                    Toast.makeText(MainActivity.this,"Thêm Nhân Viên Thành Công :)..!",Toast.LENGTH_SHORT).show();
                    nhanVienDTOList.add(nhanVienDTO);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
                break;
            case R.id.btn_Xoa:
                NhanVienDTO nhanVienDTO1;
                nhanVienDTO1 = nhanVienDTOList.get(0);
                Log.d("MainActivity", String.valueOf(nhanVienDTO1.get_id()));
                nhanVienDAO.xoaDuLieu(nhanVienDTO1);
//                nhanVienAdapter.notifyItemRemoved(0);
                nhanVienDTOList.remove(nhanVienDTO1);
                nhanVienAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_Sua:
                break;
                default:
                    break;
        }

    }

    @Override
    public int getCount() {
        if(nhanVienDTOList.size()== 0){
            return 0;
        }
        return nhanVienDTOList.size();
    }

    @Override
    public NhanVienDTO getData(int i) {
        return nhanVienDTOList.get(i);
    }
}
