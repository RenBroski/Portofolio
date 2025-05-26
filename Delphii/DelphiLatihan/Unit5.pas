unit Unit5;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Data.DB, System.Rtti,
  System.Bindings.Outputs, Vcl.Bind.Editors, Data.Bind.EngExt,
  Vcl.Bind.DBEngExt, Data.Bind.Components, Data.Bind.DBScope, Vcl.Grids,
  Vcl.DBGrids, Vcl.StdCtrls, Vcl.ComCtrls, Vcl.Mask, Vcl.DBCtrls, Vcl.Buttons,
  Vcl.ToolWin, Data.Win.ADODB;

type
  TForm5 = class(TForm)
    ADOConnection1: TADOConnection;
    guru: TADOQuery;
    DataSource1: TDataSource;
    ToolBar1: TToolBar;
    Tambah: TSpeedButton;
    Simpan: TSpeedButton;
    Ubah: TSpeedButton;
    Hapus: TSpeedButton;
    Batal: TSpeedButton;
    Tutup: TSpeedButton;
    gurukodeGuru: TStringField;
    gurunama: TStringField;
    guruumur: TStringField;
    gurumapel: TStringField;
    guruagama: TStringField;
    gurutglLahir: TDateField;
    Label1: TLabel;
    DBEdit1: TDBEdit;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
    DBEdit4: TDBEdit;
    Label5: TLabel;
    Label6: TLabel;
    DateTimePicker1: TDateTimePicker;
    ComboBox1: TComboBox;
    DBGrid1: TDBGrid;
    BindSourceDB1: TBindSourceDB;
    BindingsList1: TBindingsList;
    LinkFillControlToField1: TLinkFillControlToField;
    LinkControlToField1: TLinkControlToField;
    procedure TambahClick(Sender: TObject);
    procedure SimpanClick(Sender: TObject);
    procedure UbahClick(Sender: TObject);
    procedure HapusClick(Sender: TObject);
    procedure BatalClick(Sender: TObject);
    procedure TutupClick(Sender: TObject);
  private
    { Private declarations }
    procedure CekDanBukaDataset;
  public
    { Public declarations }
  end;

var
  Form5: TForm5;

implementation

{$R *.dfm}
procedure TForm5.BatalClick(Sender: TObject);
begin
Tambah.Enabled := True;
  Ubah.Enabled := True;
  Hapus.Enabled := True;
  Batal.Enabled := False;
  Simpan.Enabled := True;
  Tutup.Enabled := True;

  CekDanBukaDataset;
  guru.Cancel;
end;

procedure TForm5.CekDanBukaDataset;
begin
  if not ADOConnection1.Connected then
    ADOConnection1.Connected := True;

  if not guru.Active then
    guru.Open;
end;

procedure TForm5.HapusClick(Sender: TObject);
begin
CekDanBukaDataset;

  if not guru.IsEmpty then
    guru.Delete
  else
    ShowMessage('Data kosong, tidak dapat dihapus.');
end;

procedure TForm5.SimpanClick(Sender: TObject);
begin
Tambah.Enabled := True;
  Ubah.Enabled := True;
  Hapus.Enabled := True;
  Batal.Enabled := False;
  Simpan.Enabled := False;
  Tutup.Enabled := True;

  CekDanBukaDataset;
  guru.Post;
end;

procedure TForm5.TambahClick(Sender: TObject);
begin
Tambah.Enabled := False;
  Ubah.Enabled := False;
  Hapus.Enabled := True;
  Batal.Enabled := False;
  Simpan.Enabled := True;
  Tutup.Enabled := True;

  CekDanBukaDataset;
  guru.Insert;
end;

procedure TForm5.TutupClick(Sender: TObject);
begin
 application.Terminate;
end;

procedure TForm5.UbahClick(Sender: TObject);
begin
Tambah.Enabled := False;
  Ubah.Enabled := False;
  Hapus.Enabled := True;
  Batal.Enabled := True;
  Simpan.Enabled := True;
  Tutup.Enabled := False;

  CekDanBukaDataset;
  guru.Edit;
end;

end.
