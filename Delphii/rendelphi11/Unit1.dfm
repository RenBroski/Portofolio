object Form1: TForm1
  Left = 0
  Top = 0
  Caption = 'Form1'
  ClientHeight = 504
  ClientWidth = 255
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 16
    Top = 8
    Width = 194
    Height = 16
    Caption = 'Simulasi Perhitungan Tarif PLN'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Tahoma'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object LabelPemakaian: TLabel
    Left = 16
    Top = 272
    Width = 51
    Height = 13
    Caption = 'Pemakaian'
  end
  object LabelBiayaBeban: TLabel
    Left = 16
    Top = 312
    Width = 59
    Height = 13
    Caption = 'Biaya Beban'
  end
  object LabelBiayaPemakaian: TLabel
    Left = 16
    Top = 336
    Width = 80
    Height = 13
    Caption = 'Biaya Pemakaian'
  end
  object LabelPPJU: TLabel
    Left = 16
    Top = 371
    Width = 44
    Height = 13
    Caption = 'PPJU 3%'
  end
  object LabelMaterai: TLabel
    Left = 16
    Top = 390
    Width = 36
    Height = 13
    Caption = 'Materai'
  end
  object LabelTotalTagihan: TLabel
    Left = 16
    Top = 440
    Width = 65
    Height = 13
    Caption = 'Total Tagihan'
  end
  object RadioGroupDaya: TDBRadioGroup
    Left = 8
    Top = 149
    Width = 231
    Height = 105
    Caption = 'Daya'
    TabOrder = 0
  end
  object RadioGroupGolongan: TDBRadioGroup
    Left = 8
    Top = 38
    Width = 231
    Height = 105
    Caption = 'Golongan Tarif'
    Items.Strings = (
      'Sosial'
      'Rumah Tangga'
      'Bisnis')
    TabOrder = 1
  end
  object EditPemakaian: TEdit
    Left = 118
    Top = 269
    Width = 121
    Height = 21
    TabOrder = 2
    Text = '0'
  end
  object EditBiayaBeban: TEdit
    Left = 118
    Top = 309
    Width = 121
    Height = 21
    TabOrder = 3
    Text = '0'
  end
  object EditBiayaPemakaian: TEdit
    Left = 118
    Top = 333
    Width = 121
    Height = 21
    TabOrder = 4
    Text = '0'
  end
  object EditPPJU: TEdit
    Left = 118
    Top = 360
    Width = 121
    Height = 21
    TabOrder = 5
    Text = '0'
  end
  object EditMaterai: TEdit
    Left = 118
    Top = 387
    Width = 121
    Height = 21
    TabOrder = 6
    Text = '0'
  end
  object EditTotalTagihan: TEdit
    Left = 126
    Top = 437
    Width = 121
    Height = 21
    TabOrder = 7
    Text = '0'
  end
  object ButtonHitung: TButton
    Left = 152
    Top = 471
    Width = 75
    Height = 25
    Caption = 'Hitung'
    TabOrder = 8
  end
end
