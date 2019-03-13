package rikkesoft.trongvu.com.sqlite_832019;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.security.cert.PolicyNode;

import rikkesoft.trongvu.com.sqlite_832019.DTO.NhanVienDTO;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {

    private DuLieu duLieu;
    private Context context;
    public NhanVienAdapter(DuLieu duLieu,Context context){
        this.duLieu = duLieu;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =  inflater.inflate(R.layout.activity_nhanvienitem,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final NhanVienDTO nhanVienDTO = duLieu.getData(i);
        viewHolder.tv_idNV.setText(String.valueOf(nhanVienDTO.get_id()));
        viewHolder.tv_tenNV.setText(nhanVienDTO.getTenNhanVien());
        viewHolder.tv_tenNV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context,viewHolder.tv_tenNV);
                popup.inflate(R.menu.context_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_xoa:
                                break;
                            case R.id.menu_sua:
                                break;
                                default:
                                    break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });

    }


    @Override
    public int getItemCount() {
        return duLieu.getCount();
    }

    public interface DuLieu{
        int getCount();
        NhanVienDTO getData(int i);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tenNV;
        private TextView tv_idNV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenNV = itemView.findViewById(R.id.tv_TenNhanVien);
            tv_idNV = itemView.findViewById(R.id.tv_IDNhanVien);

        }
    }
}
