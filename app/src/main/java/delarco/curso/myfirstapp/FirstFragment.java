package delarco.curso.myfirstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import delarco.curso.myfirstapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

  /*      binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se obtienen datos del correo
                EditText etEmail = (EditText) binding.etEmail;
                EditText etSubject = (EditText) binding.etSubject;
                EditText etBody = (EditText) binding.etBody;
                CheckBox chkAttachment = (CheckBox) binding.chkAttachment;

                // Intent para levantar la actividad
                Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
                // Se envía texto plano  salvo que el checkbox esté marcado
                itSend.setType("plain/text");

                // Se colocan datos para el envío
                itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ etEmail.getText().toString()});
                itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                itSend.putExtra(android.content.Intent.EXTRA_TEXT, etBody.getText());

                //Se inicia la actividad

                startActivity(itSend);

                // Se revisa si el checkbox está marcado enviamos el ícono de la aplicación como adjunto
                if (chkAttachment.isChecked()) {
                    // Se coloca el adjunto en el stream

                    itSend.putExtra(Intent.EXTRA_STREAM,  Uri.parse("android.resource://delarco.curso.myfirstapp/" + R.mipmap.ic_entrega_launcher));

                    // Se indica el tipo de dato
                    itSend.setType("image/png");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}